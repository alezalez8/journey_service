package org.hillel.service;

public class SearchQueryParam {
    private int totalRecords; // общее кол-во записей
    private int pageIndex; // номер страницы, с которой начинается вывод
    private int maxResult; // кол-во записей на странице
    private int fromRecordIndex; // с какой записи в БД начинаем выводить
    private int totalPages; // кол-во выводимых страниц
    private String sortBy = null; // запрос параметра, по которому осушествляется сортировка
    boolean isAscSort = true; //  сортировка по возрастанию/убыванию
    private int maxRecordIndex;

//https://betacode.net/11797/pagination-in-java-hibernate

   // new SearchQueryParam(pageIndex, maxResult, sortBy, isAscSort)


    public SearchQueryParam(int fromPage, int recordOnPage, String sortBy, boolean isAscSort) {

        this.pageIndex = Math.max(fromPage - 1, 0);
        this.fromRecordIndex = pageIndex * recordOnPage;
        this.maxResult = recordOnPage;
        this.maxRecordIndex = fromRecordIndex + maxResult;
        this.isAscSort = isAscSort;
        this.sortBy = sortBy;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isAscSort() {
        return isAscSort;
    }

    public String getQueryParam() {
        if (sortBy != null) {
            return " order by " + sortBy + (isAscSort ? " asc" : " desc");
        }
        return "";
    }
}
