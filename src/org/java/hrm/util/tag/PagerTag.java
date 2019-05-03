package org.java.hrm.util.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PagerTag extends SimpleTagSupport {

    /** 请求的URL中使用的占位符 */
    private static final String TAG = "{0}";
    /** 当前页码 */
    private int pageIndex;
    /** 页的大小 */
    private int pageSize;
    /** 总的记录数 */
    private int recordCount;
    /** 要请求的URL */
    private String submitUrl;
    /** 总的页数 */
    private int totalPage = 0;

    @Override
    public void doTag() throws JspException, IOException {
        StringBuilder res = new StringBuilder();
        StringBuilder str = new StringBuilder();

        if (this.recordCount > 0){
            this.totalPage = (this.recordCount - 1) / this.pageSize + 1;

            if (this.pageIndex == 1){// 如果当前页是第一页
                str.append("<span class='disabled'>上一页</span>");
                /** 计算中间的标签 */
                this.calcPage(str);
                if (this.pageIndex == this.totalPage) {//如果当前页既是第一页也是最后一页
                    str.append("<span class='disabled'>下一页</span>");
                }else{//如果当前页是第一页但不是最后一页
                    String temp = this.submitUrl.replace(TAG, String.valueOf(this.pageIndex + 1));
                    str.append("<a href='" + temp + "'>下一页</a>");
                }
            }else if (this.pageIndex == this.totalPage) {//如果当前页是最后一页
                String temp = this.submitUrl.replace(TAG, String.valueOf(this.pageIndex - 1));
                str.append("<a href='" + temp + "'>上一页</a>");
                this.calcPage(str);
                str.append("<span class='disabled'>下一页</span>");
            }else {
                String temp = this.submitUrl.replace(TAG, String.valueOf(this.pageIndex - 1));
                str.append("<a href='" + temp + "'>上一页</a>");
                this.calcPage(str);
                temp = this.submitUrl.replace(TAG, String.valueOf(this.pageIndex + 1));
                str.append("<a href='" + temp + "'>下一页</a>");
            }

            /** 暂时先输出没样式的分页 */
            this.getJspContext().getOut().print(str.toString());
        }
    }


    private void calcPage(StringBuilder str) {
        if (this.totalPage <= 11){//当前总页数较少,可以全部显示
            for (int i = 1; i <= this.totalPage; i++) {
                if (i == this.pageIndex) {
                    str.append("<span class='current'>" + i + "</span>");
                }else {
                    String temp = this.submitUrl.replace(TAG, String.valueOf(i));
                    str.append("<a href='" + temp + "'>" + i + "</a>");
                }
            }

        }else {
            if (this.pageIndex <= 8) {// 当前页码比较靠前
                for (int i = 1; i <= 10; i++) {
                    if (i == this.pageIndex) {
                        str.append("<span class='current'>" + i + "</span>");
                    }else {
                        String temp = this.submitUrl.replace(TAG, String.valueOf(i));
                        str.append("<a href='" + temp + "'>" + i + "</a>");
                    }
                }
                str.append("...");
                String temp = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
                str.append("<a href='" + temp + "'>" + this.totalPage + "</a>");
            }else if (this.pageIndex + 8 >= this.totalPage) {// 当前页码比较靠后
                String temp = this.submitUrl.replace(TAG, String.valueOf(1));
                str.append("<a href='" + temp + "'>" + 1 + "</a>");
                str.append("...");
                for (int i = this.totalPage - 8; i < this.totalPage; i++){
                    if (i == this.pageIndex) {
                        str.append("<span class='current'>" + i + "</span>");
                    }else {
                        temp = this.submitUrl.replace(TAG, String.valueOf(i));
                        str.append("<a href='" + temp + "'>" + i + "</a>");
                    }
                }
            }else {// 当前页码处于中间位置
                String temp = this.submitUrl.replace(TAG, String.valueOf(1));
                str.append("<a href='" + temp + "'>" + 1 + "</a>");
                str.append("...");

                for (int i = this.pageIndex - 4; i < this.pageIndex + 4; i++) {
                    if (i == this.pageIndex) {
                        str.append("<span class='current'>" + i + "</span>");
                    }else {
                        temp = this.submitUrl.replace(TAG, String.valueOf(i));
                        str.append("<a href='" + temp + "'>" + i + "</a>");
                    }
                }

                str.append("...");
                temp = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
                str.append("<a href='" + temp + "'>" + this.totalPage + "</a>");
            }
        }
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }
}
