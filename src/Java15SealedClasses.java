public class Java15SealedClasses {

}


//разрешает наследовать себя только классам B,C,D
sealed class A permits B,C,D {}


//разрешает наследовать себя кому-угодно
non-sealed class B extends A {}
//разрешает наследовать себя классу E
sealed class C extends A permits E{}
//запрещает наследовать себя
final class D extends A {}



final class E extends C {}

class F extends B {}