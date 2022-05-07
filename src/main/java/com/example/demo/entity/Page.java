package com.example.demo.entity;

/**
 * 封装分页相关的信息
 */
public class Page {

    // 当前页码
    private int currentPage = 1;
    // 一页最多显示多少条数据
    private int limit = 10;
    // 数据总数（用于计算总页数）
    private int rows;
    // 查询路径（用于复用分页链接）
    private String path;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage >= 1) {
            this.currentPage = currentPage;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >=1 && limit <= 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     *
     * @return
     */
    public int getOffset(){
        // currentPage * limit - limit
        return (currentPage - 1) * limit;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public int getTotal(){
        // rows / limit
        if (rows % limit == 0){
            return rows / limit;
        } else {
            return rows / limit + 1;
        }
    }

    /**
     *  获取起始页码缓存当前页码的周边页码
     *
     * @return
     */
    public int getFromPage(){
        int fromPage = currentPage - 2;
        return Math.max(fromPage, 1);
    }

    /**
     *获取结束页码缓存当前页码的周边页码
     *
     * @return
     */
    public int getToPage(){
        int toPage = currentPage + 2;
        return Math.min(toPage, this.getTotal());
    }

}
