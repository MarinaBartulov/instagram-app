package com.itengine.instagram.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FollowId {

    @Column(name="followed_id")
    private Long followedId;
    @Column(name="follower_id")
    private Long followerId;
}
