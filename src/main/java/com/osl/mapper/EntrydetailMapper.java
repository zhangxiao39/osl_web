package com.osl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.osl.model.EntrydetailModel;

@Mapper
public interface EntrydetailMapper {
	
	public List<EntrydetailModel> bQueryEntryDetailListByEntryId(String entryId);
	
	public int bupdateStatusByEntryId(String entryId);
	
	public int  bDeleteEntryDetailById(String entryDetailId);
	
	public int bPhysicsDeleteEntryDetailById(String entryDetailId);
	
	public List<EntrydetailModel> bQueryEntryDetailListByCondition(EntrydetailModel entryDetailModel);
	
	public int bupdateInputNumsAndNumbers(EntrydetailModel entryDetailModel);
}
