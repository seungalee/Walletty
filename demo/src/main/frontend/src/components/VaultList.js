import { useEffect, useState } from "react";
import VaultItem from "./VaultItem";
import { useNavigate } from "react-router-dom";

const VaultList = ({ totalVaultList }) => {
  const filteredVaultList = totalVaultList.filter((it) => !it.outSafe);

  const navigate = useNavigate();
  useEffect(() => {
    const checkList = filteredVaultList.map((it) => {
      if (!it.accept && it.now === "True") {
        if (
          window.confirm(
            "아직 수락하지 않은 미션이 있습니다. 피드백 페이지로 먼저 이동합니다."
          )
        ) {
          navigate("/feedback");
        } else {
          navigate("/feedback");
        }
      }
      console.log(filteredVaultList);
    });
  }, [filteredVaultList]);
  return (
    <div className="VaultList">
      {filteredVaultList.map((it) => (
        <VaultItem key={it.missionId} {...it} />
      ))}
    </div>
  );
};

export default VaultList;
