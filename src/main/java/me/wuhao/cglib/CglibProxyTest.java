package me.wuhao.cglib;

/**
 * Created by everseeker on 2017/10/24.
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        Essay essay = (Essay)new CglibProxy().getProxyObject(new Essay("操作用户正在浏览的页面", "通过Chrome扩展我们可以对用户当前浏览的页面进行操作，实际上就是对用户当前浏览页面的DOM进行操作。通过Manifest中的content_scripts属性可以指定将哪些脚本何时注入到哪些页面中，当用户访问这些页面后，相应脚本即可自动运行，从而对页面DOM进行操作。\n" +
                "Manifest的content_scripts属性值为数组类型，数组的每个元素可以包含matches、exclude_matches、css、js、run_at、all_frames、include_globs和exclude_globs等属性。其中matches属性定义了哪些页面会被注入脚本，exclude_matches则定义了哪些页面不会被注入脚本，css和js对应要注入的样式表和JavaScript，run_at定义了何时进行注入，all_frames定义脚本是否会注入到嵌入式框架中，include_globs和exclude_globs则是全局URL匹配，最终脚本是否会被注入由matches、exclude_matches、include_globs和exclude_globs的值共同决定。简单的说，如果URL匹配mathces值的同时也匹配include_globs的值，会被注入；如果URL匹配exclude_matches的值或者匹配exclude_globs的值，则不会被注入。\n" +
                "content_scripts中的脚本只是共享页面的DOM1，而并不共享页面内嵌JavaScript的命名空间。也就是说，如果当前页面中的JavaScript有一个全局变量a，content_scripts中注入的脚本也可以有一个全局变量a，两者不会相互干扰。当然你也无法通过content_scripts访问到页面本身内嵌JavaScript的变量和函数。"));
        System.out.println("printEssay start...\n");
        essay.printEssay();
        System.out.println("\ntoString start...");
    }
}
