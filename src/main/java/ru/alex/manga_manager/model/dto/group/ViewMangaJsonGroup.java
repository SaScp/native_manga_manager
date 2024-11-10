package ru.alex.manga_manager.model.dto.group;

public class ViewMangaJsonGroup {
    public abstract interface Title{}
    public interface InnerDataTitle extends Title{}
    public interface CatalogDataTitle extends Title{}
    public interface SearchDataTitle extends Title{}
}
