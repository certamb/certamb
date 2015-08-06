package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.sistcoop.cooperativa.models.search.OrderByModel;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

/**
 * A base class that JPA storage impls can extend.
 *
 * @author eric.wittmann@redhat.com
 */
public abstract class AbstractHibernateStorage {

    /**
     * Constructor.
     */
    public AbstractHibernateStorage() {

    }

    protected abstract EntityManager getEntityManager();

    protected Session getSession() {
        return getEntityManager().unwrap(Session.class);
    }

    protected <T> SearchResultsModel<T> findFullText(SearchCriteriaModel criteria, Class<T> type,
            String filterText, String... field) {

        SearchResultsModel<T> results = new SearchResultsModel<>();
        Session session = getSession();

        // Set some default in the case that paging information was not
        // included in the request.
        PagingModel paging = criteria.getPaging();
        if (paging == null) {
            paging = new PagingModel();
            paging.setPage(1);
            paging.setPageSize(20);
        }
        int page = paging.getPage();
        int pageSize = paging.getPageSize();
        int start = (page - 1) * pageSize;

        Criteria criteriaQuery = session.createCriteria(type);
        applySearchCriteriaToQuery(criteria, type, criteriaQuery, false);

        // filter text
        List<Criterion> disjuntions = new ArrayList<>();
        for (String fieldName : field) {
            Criterion criterion = Restrictions.ilike(fieldName, filterText, MatchMode.ANYWHERE);
            disjuntions.add(criterion);
        }
        criteriaQuery.add(Restrictions.or(disjuntions.toArray(new Criterion[disjuntions.size()])));

        // paging
        criteriaQuery.setFirstResult(start);
        criteriaQuery.setMaxResults(pageSize + 1);
        boolean hasMore = false;

        // Now query for the actual results
        @SuppressWarnings("unchecked")
        List<T> resultList = criteriaQuery.list();

        // Check if we got back more than we actually needed.
        if (resultList.size() > pageSize) {
            resultList.remove(resultList.size() - 1);
            hasMore = true;
        }

        // If there are more results than we needed, then we will need to do
        // another
        // query to determine how many rows there are in total
        int totalSize = start + resultList.size();
        if (hasMore) {
            totalSize = executeCountQuery(criteria, session, type);
        }
        results.setTotalSize(totalSize);
        results.setModels(resultList);
        return results;
    }

    protected <T> SearchResultsModel<T> find(SearchCriteriaModel criteria, Class<T> type) {
        SearchResultsModel<T> results = new SearchResultsModel<>();
        Session session = getSession();

        // Set some default in the case that paging information was not
        // included in the request.
        PagingModel paging = criteria.getPaging();
        if (paging == null) {
            paging = new PagingModel();
            paging.setPage(1);
            paging.setPageSize(20);
        }
        int page = paging.getPage();
        int pageSize = paging.getPageSize();
        int start = (page - 1) * pageSize;

        Criteria criteriaQuery = session.createCriteria(type);
        applySearchCriteriaToQuery(criteria, type, criteriaQuery, false);
        criteriaQuery.setFirstResult(start);
        criteriaQuery.setMaxResults(pageSize + 1);
        boolean hasMore = false;

        // Now query for the actual results
        @SuppressWarnings("unchecked")
        List<T> resultList = criteriaQuery.list();

        // Check if we got back more than we actually needed.
        if (resultList.size() > pageSize) {
            resultList.remove(resultList.size() - 1);
            hasMore = true;
        }

        // If there are more results than we needed, then we will need to do
        // another
        // query to determine how many rows there are in total
        int totalSize = start + resultList.size();
        if (hasMore) {
            totalSize = executeCountQuery(criteria, session, type);
        }
        results.setTotalSize(totalSize);
        results.setModels(resultList);
        return results;
    }

    protected <T> int executeCountQuery(SearchCriteriaModel criteria, Session session, Class<T> type) {
        Criteria criteriaQuery = session.createCriteria(type);
        applySearchCriteriaToQuery(criteria, type, criteriaQuery, true);
        criteriaQuery.setProjection(Projections.rowCount());
        return (Integer) criteriaQuery.uniqueResult();
    }

    protected <T> void applySearchCriteriaToQuery(SearchCriteriaModel criteria, Class<T> type,
            Criteria criteriaQuery, boolean countOnly) {
        List<SearchCriteriaFilterModel> filters = criteria.getFilters();
        if (filters != null && !filters.isEmpty()) {
            for (SearchCriteriaFilterModel filter : filters) {
                if (filter.getOperator() == SearchCriteriaFilterOperator.eq) {
                    criteriaQuery.add(Restrictions.eq(filter.getName(), filter.getValue()));
                } else if (filter.getOperator() == SearchCriteriaFilterOperator.bool_eq) {
                    criteriaQuery.add(Restrictions.eq(filter.getName(), filter.getValue()));
                } else if (filter.getOperator() == SearchCriteriaFilterOperator.gt) {
                    criteriaQuery.add(Restrictions.gt(filter.getName(), filter.getValue()));
                } else if (filter.getOperator() == SearchCriteriaFilterOperator.gte) {
                    criteriaQuery.add(Restrictions.ge(filter.getName(), filter.getValue()));
                } else if (filter.getOperator() == SearchCriteriaFilterOperator.lt) {
                    criteriaQuery.add(Restrictions.lt(filter.getName(), filter.getValue()));
                } else if (filter.getOperator() == SearchCriteriaFilterOperator.lte) {
                    criteriaQuery.add(Restrictions.le(filter.getName(), filter.getValue()));
                } else if (filter.getOperator() == SearchCriteriaFilterOperator.neq) {
                    criteriaQuery.add(Restrictions.ne(filter.getName(), filter.getValue()));
                } else if (filter.getOperator() == SearchCriteriaFilterOperator.like) {
                    criteriaQuery.add(Restrictions.like(filter.getName(), (String) filter.getValue(),
                            MatchMode.ANYWHERE));
                }
            }
        }
        List<OrderByModel> orders = criteria.getOrders();
        if (orders != null && !orders.isEmpty() && !countOnly) {
            for (OrderByModel orderBy : orders) {
                if (orderBy.isAscending()) {
                    criteriaQuery.addOrder(Order.asc(orderBy.getName()));
                } else {
                    criteriaQuery.addOrder(Order.desc(orderBy.getName()));
                }
            }
        }
    }
}