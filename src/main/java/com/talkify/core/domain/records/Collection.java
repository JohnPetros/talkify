package com.talkify.core.domain.records;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public record Collection<Item>(List<Item> items) {

  public static <Item> Collection<Item> create(List<Item> items) {
    return new Collection<Item>(items);
  }

  public static <Item, NewItem> Collection<NewItem> createFrom(
      List<Item> items, Function<Item, NewItem> mapper) {
    var list = items
        .stream()
        .map(mapper)
        .collect(Collectors.toList());
    return new Collection<>(list);
  }

  public Collection<Item> add(Item item) {
    items.add(item);
    return new Collection<>(items);
  }

  public Collection<Item> remove(Item item) {
    items.remove(item);
    return new Collection<>(items);
  }

  public <NewItem> Collection<NewItem> map(Function<Item, NewItem> mapper) {
    var list = items
        .stream()
        .map(mapper)
        .collect(Collectors.toList());
    return new Collection<>(list);
  }
}
