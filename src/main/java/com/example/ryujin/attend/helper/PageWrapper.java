package com.example.ryujin.attend.helper;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PageWrapper<T> {

    private final Page<T> page;
    private final List<PageItem> pageNumbers;
    private final int blockSize;

    public PageWrapper(Page<T> page,  int blockSize) {
        this.page = page;
        this.blockSize = blockSize;
        this.pageNumbers = createPageNumbers();
    }

    private List<PageItem> createPageNumbers() {
        int totalPages = page.getTotalPages();
        int currentPage = page.getNumber();

        int startPage = (currentPage / blockSize) * blockSize;
        int endPage = Math.min(startPage + blockSize -1, totalPages - 1);

        if (totalPages == 0) {
            return List.of();
        }
        return IntStream.rangeClosed(startPage, endPage)
                .mapToObj(i -> new PageItem(i, i + 1, (i == currentPage)))
                .collect(Collectors.toList());
    }

    public static class PageItem {
        public int value;
        public int displayValue;
        public boolean current;

        public PageItem(int value, int displayValue, boolean current) {
            this.value = value;
            this.displayValue = displayValue;
            this.current = current;
        }
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public int getPreviousPageNumber() {
        return page.getNumber() - 1;
    }

    public int getNextPageNumber() {
        return page.getNumber() + 1;
    }

    public int getSize() {
        return page.getSize();
    }

    public int getTotalElements() {
        return (int) page.getTotalElements();
    }
}
