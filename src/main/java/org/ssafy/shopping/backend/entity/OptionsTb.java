package org.ssafy.shopping.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "items_tb")
public class OptionsTb {
    @EmbeddedId
    private Option id;

}
