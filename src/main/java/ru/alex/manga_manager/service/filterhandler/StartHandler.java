package ru.alex.manga_manager.service.filterhandler;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.alex.manga_manager.model.data.entity.FilterEntity;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.repository.MangaRepository;

import java.util.List;

public class StartHandler extends Handler{
    public StartHandler(MangaRepository mangaRepository) {
        super(mangaRepository);
    }

    @Override
    public List<Manga> handleRequest(FilterEntity filterEntity) {
        checkOrderOnStartsWithPlus(filterEntity.getOrder());
        if (filterEntity.getOrder() != null) {
            Sort sort = orderFlag ? Sort.by(this.order).descending() : Sort.by(this.order).ascending();
            pageRequest = PageRequest.of(filterEntity.getPageNumber(),filterEntity.getPageSize(), sort);
        } else {
            pageRequest = PageRequest.of(filterEntity.getPageNumber(),filterEntity.getPageSize());
        }
        return nextHandler.handleRequest(filterEntity);
    }

    private void checkOrderOnStartsWithPlus(String order) {
        if (order != null) {
            this.orderFlag = order.startsWith(" ");
            this.order = order.substring(1);
        }
    }
}
