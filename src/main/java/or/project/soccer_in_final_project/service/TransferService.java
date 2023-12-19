package or.project.soccer_in_final_project.service;

import or.project.soccer_in_final_project.dto.*;
import or.project.soccer_in_final_project.entities.Transfer;

import java.util.List;

public interface TransferService {
    TransferResponseDto createTransfer(TransferRequestDto transferRequestDto);

    TransferResponseDto updateTransferById(TransferRequestDto dto, long id);

    TransferResponseDto findTransferById(long id);
    TransferFindByNameResponseDto findTransfersByPlayerName(String playerFullName);
    TransferFindByTeamDto findTransfersByTeamName(String teamName);
    TransferFindByRangeFeeDto findTransfersByRangeOfFees(int rangeFrom,int rangeTo);

    TransferResponseDto deleteTransferById(long id);

    List<TransferResponseDto> getAllTransfers();


    TransferPageResponseDto getAllTransfers(int pageNo, int pageSize, String sortBy, String sortDir);
}
