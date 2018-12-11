package com.osl.service;

import java.util.List;

import com.osl.model.ShelvesModel;

public interface ShelvesService {
	public List<ShelvesModel> findShelvesAll(int bid);
}
