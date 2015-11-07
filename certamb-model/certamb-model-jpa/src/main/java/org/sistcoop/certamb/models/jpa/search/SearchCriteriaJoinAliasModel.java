package org.sistcoop.certamb.models.jpa.search;

/**
 * A base class that JPA storage impls can extend.
 *
 * @author eric.wittmann@redhat.com
 */
public class SearchCriteriaJoinAliasModel {

    private String associationPath;
    private String associationAlias;
    private SearchCriteriaJoinType joinType;

    public SearchCriteriaJoinAliasModel() {

    }

    public String getAssociationPath() {
        return associationPath;
    }

    public void setAssociationPath(String associationPath) {
        this.associationPath = associationPath;
    }

    public String getAssociationAlias() {
        return associationAlias;
    }

    public void setAssociationAlias(String associationAlias) {
        this.associationAlias = associationAlias;
    }

    public SearchCriteriaJoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(SearchCriteriaJoinType joinType) {
        this.joinType = joinType;
    }

}