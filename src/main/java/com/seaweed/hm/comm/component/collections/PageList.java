package com.seaweed.hm.comm.component.collections;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Getter
@ToString
public class PageList<T> {
    private Pagenation pageInfo;
    private List<T> content;

    public PageList(Page page, Class<?> entityClass,Class<T> dtoClass) throws NoSuchMethodException {
        pageInfo = new Pagenation();
        pageInfo.setPage(page.getNumber());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setSize(page.getSize());
        pageInfo.setTotalItems(page.getTotalElements());
        Constructor<T> constructor = dtoClass.getConstructor(entityClass);
        this.content = page.getContent().stream().map(entity -> {
            try {
                return constructor.newInstance(entity);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    @Getter
    @Setter
    class Pagenation {
        private int totalPages;
        private int page;
        private int size;
        private long totalItems;
    }

}
