package com.example.demo.designPattern.decorator;

//ConreteDecorator 技能：R
public class Skill_R extends HeroDecorator {

    private String skillName;

    public Skill_R(Hero hero,String skillName) {
        super(hero);
        this.skillName = skillName;
    }

    @Override
    public void learnSkills() {
        System.out.println("学习了技能R:" +skillName );
        super.learnSkills();
    }
}