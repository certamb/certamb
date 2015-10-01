/**
 * 
 * GenericGenerator 
 * */
@GenericGenerator(name = "SgGenericGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @Parameter(name = "prefer_sequence_per_entity", value = "true"),
        @Parameter(name = "optimizer ", value = "pooled") })
/**
 * @author carlosthe19916@gmail.com
 *
 */
package org.sistcoop.cooperativa.models.jpa.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

