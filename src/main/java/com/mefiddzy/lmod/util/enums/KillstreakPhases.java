package com.mefiddzy.lmod.util.enums;

import com.mefiddzy.lmod.util.classVariables.Interval;

public enum KillstreakPhases {
    p1(2.3f, new Interval(0, 5)),
    p2(3.5f, new Interval(6, 15)),
    p3(4.7f, new Interval(16, 25)),
    p4(5.7f, new Interval(26, 30)),
    p5(7f, new Interval(31, 35)),
    p6(8.7f, new Interval(36, 45)),
    p7(10.7f, new Interval(46, 50)),
    p8(-2f, new Interval(51, 60)),
    p9(16.7f, new Interval(61, 75)),
    p10(19.7432f, new Interval(76, Integer.MAX_VALUE)),
    ;

    private final float atackDmg;
    private final Interval kills;
    public static final int killstreakPhasesEnd = 100;


    public static KillstreakPhases[] getAllPhases() {
        KillstreakPhases ph[] = KillstreakPhases.values();
        return ph;
    }

    public static KillstreakPhases getType(int kills) {
        KillstreakPhases ph[] = getAllPhases();
        Interval[] intervals = new Interval[ph.length];

        for (int i = 0; i < ph.length; i++) {
            intervals[i] = ph[i].getKills();
        }

        for (int i = 0; i < ph.length; i++) {
            if (intervals[i].between(kills)) {
                return ph[i];
            }
        }
        return ph[0];
    }

    public float getAttackDmg() {
        return atackDmg;
    }

    public Interval getKills() {
        return kills;
    }

    public int getPhaseNumber() {
        KillstreakPhases ph[] = this.getAllPhases();

        for (int i = 0; i < ph.length; i++) {
            if (ph[i] == this)
                return i + 1;
        }

        return -1;
    }

    KillstreakPhases(float atackDmg, Interval kills) {
        this.atackDmg = atackDmg;
        this.kills = kills;
    }
}
