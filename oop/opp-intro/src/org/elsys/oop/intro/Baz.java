package org.elsys.oop.intro;

public class Baz extends Foo {
    void func() {
        Foo f = new Foo();
//        f.a
    }

    @Override
    int pow2(){
        return (int)Math.pow(a, 2);
    }
}
