package com.upload.upload_Game.pojo;
public enum ChallengeType {
    /**
     * There are three levels of difficulty
     */
    EASY(1),
    MEDIUM(2),
    HARD(3);

    private int typecode;
    ChallengeType(int typecode){
        this.typecode=typecode;
    }

    public int getTypecode() {
        return typecode;
    }


}
