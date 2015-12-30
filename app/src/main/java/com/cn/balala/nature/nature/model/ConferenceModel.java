package com.cn.balala.nature.nature.model;

/**
 * Created by Administrator on 2015/12/30.
 */
public class ConferenceModel {
    public long conferenceId;
    public String conferenceName;
    public String conferenceAddress;
    public String conferencePerson;
    public String conferenceTime;
    public int conferenceState;

    public ConferenceModel(long conferenceId,String conferenceName,String conferenceAddress,String conferencePerson,String conferenceTime,int conferenceState) {
        this.conferenceName = conferenceName;
        this.conferenceAddress = conferenceAddress;
        this.conferencePerson = conferencePerson;
        this.conferenceTime = conferenceTime;
        this.conferenceState = conferenceState;

    }
}
