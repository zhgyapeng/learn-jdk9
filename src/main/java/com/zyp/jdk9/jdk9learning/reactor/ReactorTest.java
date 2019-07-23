package com.zyp.jdk9.jdk9learning.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zhangyp
 */
public class ReactorTest {
    public static void main(String[] args) {
        Flux<String> just = Flux.just("hello", "world");
        just.subscribe(System.out::println);

        Flux.create(fluxSink -> {
            fluxSink.next("hello");
            fluxSink.next("world");
            fluxSink.complete();
        }).subscribe(System.out::println);

        Mono.just("HelloWorld").subscribe(System.out::println);

        Flux.range(0, 100).buffer(20).subscribe(ReactorTest::print);
        System.out.println();
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        System.out.println();
        Flux.range(1, 20).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);

    }

    public static void print(Object o) {
        System.out.println(o);
    }

}
