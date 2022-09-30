package com.mindhub.skywalkies.Service.implementations;

import com.mindhub.skywalkies.Service.AvatarService;
import com.mindhub.skywalkies.models.Avatar;
import com.mindhub.skywalkies.repositories.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvatarServiceImplements implements AvatarService {
    @Autowired
    public AvatarRepository avatarRepository;
    @Override
    public List<Avatar> getAllAvatars(){return avatarRepository.findAll();}
    @Override
    public Avatar getAvatarById(long id){return  avatarRepository.findById(id).get();}
    @Override
    public void saveAvatar(Avatar avatar){avatarRepository.save(avatar);}

}
