package com.bank.app.service;

import java.util.Collection;

import com.bank.app.domain.Branch;
import org.json.JSONException;


public interface BranchService {

    public abstract String getBranchById(int id) throws JSONException;

    public abstract Branch getBranchInfo(int id);

    public abstract boolean saveBranch(Branch branch);

    public abstract boolean updateBranch(Branch branch);

    public abstract void deleteBranch(int id) throws InterruptedException;

    public abstract Collection<Branch> getAllBranches();
}
