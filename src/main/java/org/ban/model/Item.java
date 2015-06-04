package org.ban.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
public class Item {
  @Id
  @Getter @Setter
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  @Getter @Setter
  private boolean checked;

  @Column
  @Getter @Setter
  private String description;

}
