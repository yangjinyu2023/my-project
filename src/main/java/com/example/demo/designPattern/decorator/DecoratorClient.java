package com.example.demo.designPattern.decorator;

//Component：抽象组建角色，定义一组抽象的接口，规定这个被装饰组件都有哪些功能 ->Hero
//ConcreteComponent：实现这个抽象组件的所有功能 ->BlindMonk
//Decorator：装饰器角色，它持有一个Component对象实例的引用，定义一个与抽象组件一致的接口 ->HeroDecorator
//ConcreteDecorator：具体的装饰器实践者，负责实现装饰器角色定义的功能 ->Skills_Q ...
public class DecoratorClient {
    public static void main(String[] args) {
        //选择英雄
        Hero hero = new BlindMonk("李青");

        HeroDecorator heroDecorator = new HeroDecorator(hero);

        HeroDecorator r = new Skill_R(heroDecorator, "猛龙摆尾");
        r.learnSkills();
        System.out.println("=========================");
        HeroDecorator e = new Skill_E(r, "天雷破/摧筋断骨");
        HeroDecorator w = new Skill_W(e, "金钟罩/铁布衫");
        HeroDecorator q = new Skill_Q(w, "天音波/回音击");
        //学习技能
        q.learnSkills();
    }
}