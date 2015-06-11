package org.ban.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
  @Id
  private String id;

  private boolean checked;

  private String description;

}
