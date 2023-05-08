package org.ssafy.shopping.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class Option implements Serializable {
    @Column(name="items_id" ,nullable = false)
    private int itemsId;
    @Column(name="items_options_id" ,nullable = false)
    private int itemsOptionsId;

}
