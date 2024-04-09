package com.example.demo.designPattern.decorator;

//ConreteDecorator 技能：W
public class Skill_W extends HeroDecorator {

    private String skillName;

    public Skill_W(Hero hero,String skillName) {
        super(hero);
        this.skillName = skillName;
    }

    @Override
    public void learnSkills() {
        System.out.println("学习了技能W:" + skillName);
        super.learnSkills();
    }
}