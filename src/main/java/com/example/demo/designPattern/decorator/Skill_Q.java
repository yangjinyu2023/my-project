package com.example.demo.designPattern.decorator;

//ConreteDecorator 技能：Q
public class Skill_Q extends HeroDecorator {

    private String skillName;

    public Skill_Q(Hero hero,String skillName) {
        super(hero);
        this.skillName = skillName;
    }

    @Override
    public void learnSkills() {
        System.out.println("学习了技能Q:" +skillName);
        super.learnSkills();
    }
}