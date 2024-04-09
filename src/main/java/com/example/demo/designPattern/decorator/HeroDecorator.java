package com.example.demo.designPattern.decorator;

//Decorator 技能栏
public class HeroDecorator implements Hero{

    //持有一个英雄对象接口
    private Hero hero;

    public HeroDecorator(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void learnSkills() {
        if(hero != null)
            hero.learnSkills();
    }
}