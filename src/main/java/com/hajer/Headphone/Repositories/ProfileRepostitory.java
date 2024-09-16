package com.hajer.Headphone.Repositories;

import com.hajer.Headphone.Models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepostitory extends JpaRepository<Profile,Integer> {
}
