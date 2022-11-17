package com.vroong.msabootcamp.domain;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "albums")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class Album extends AuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private Instant published;

  @ManyToOne
  private Singer singer;

  @OneToMany(mappedBy = "album", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @BatchSize(size = 20)
  @OrderBy("id")
  private Set<Song> songs = new HashSet<>();

  public void addSong(Song song) {
    song.setAlbum(this);
    this.songs.add(song);
  }

  public void removeSong(Long songId) {
    this.songs = this.songs.stream()
        .filter(song -> song.getId() != songId)
        .collect(Collectors.toSet());
  }
}
