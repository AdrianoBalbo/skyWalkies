package com.mindhub.skywalkies.Service;

import com.mindhub.skywalkies.models.Avatar;

import java.util.List;

public interface AvatarService {
    public List<Avatar> getAllAvatars();

    public Avatar getAvatarById(long id);

    public void saveAvatar(Avatar avatar);
}
