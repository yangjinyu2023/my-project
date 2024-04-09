package com.example.demo.designPattern.decorator;

//ConreteDecorator 技能：E
public class Skill_E extends HeroDecorator {

    private String skillName;

    public Skill_E(Hero hero,String skillName) {
        super(hero);
        this.skillName = skillName;
    }

    @Override
    public void learnSkills() {
        System.out.println("学习了技能E:"+skillName);
        super.learnSkills();
    }
}