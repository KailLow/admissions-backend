package com.otters.admissionsbackend.command;

import com.otters.admissionsbackend.model.paper.Paper;
import com.otters.admissionsbackend.model.request.PaperRequest;
import com.otters.admissionsbackend.utils.ICopyCommand;
import com.otters.admissionsbackend.utils.IFetchCommand;

public class PaperCommand<T extends Paper, U extends PaperRequest> implements ICopyCommand<T>, IFetchCommand<T, U> {
    @Override
    public void copy(Paper copy, Paper original) {
        copy.setScore(original.getScore());
        copy.setNumberOfPapers(original.getNumberOfPapers());
        copy.setSubject(original.getSubject());
        copy.setStudent(original.getStudent());
    }

    @Override
    public void fetch(Paper original, PaperRequest request) {
        original.setScore(request.getScore());
        original.setNumberOfPapers(request.getNumberOfPapers());
    }
}
