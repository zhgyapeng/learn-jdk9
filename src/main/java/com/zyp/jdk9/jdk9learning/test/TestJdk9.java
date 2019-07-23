package com.zyp.jdk9.jdk9learning.test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author zhangyp 
 */
public class TestJdk9 {
    private static final System.Logger logger = System.getLogger("Main");
    // 接口可以定义私有方法
    interface PrivateMethodDefine {
        private String hello(){
            return "NiHao!";
        }

        default String sayWhat() {
            return hello();
        }
    }

    public static void main(String[] args) throws Exception {
        // List.of() Set.of() Map.of();
        List<String> words = List.of("hello", "world");
        System.out.println(words);
        Map<String, Integer> map = Map.of("1", 2, "2", 3);
        System.out.println(map);

        // 删除掉符合条件的数据，直到发现第一个不符合条件的数据时停止删除
        Stream.of(6, 5, 2, 4, 3).dropWhile(i -> (i > 3)).forEach(System.out::println);

        System.out.println();
        // 获取符合条件的数据，直到发现第一个不符合条件的数据时停止获取
        Stream.of(6, 5, 2, 4, 3).takeWhile(i -> (i > 3)).forEach(System.out::println);

        System.out.println();

        // ProcessHandle接口，提供原生进程监听、支持捕获结束动作并处理
        /*final ProcessBuilder builder = new ProcessBuilder("netstat").inheritIO();
        ProcessHandle processHandle = builder.start().toHandle();
        processHandle.onExit().whenComplete((handle, throwable) -> {
            if (throwable == null) {
                System.out.println(handle.pid());
            } else {
                throwable.printStackTrace();
            }
        });*/

        // logger
        logger.log(System.Logger.Level.ERROR, "This is a test error message.");


    }
}
