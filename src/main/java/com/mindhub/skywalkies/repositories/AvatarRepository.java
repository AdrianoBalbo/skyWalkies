package com.mindhub.skywalkies.repositories;

import com.mindhub.skywalkies.models.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findAvatarById(long id);
}
