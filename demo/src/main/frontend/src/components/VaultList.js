import { useEffect, useState } from "react";
import VaultItem from "./VaultItem";
import { useNavigate } from "react-router-dom";

const VaultList = ({ totalVaultList }) => {
  const filteredVaultList = totalVaultList.filter((it) => !it.outSafe);
  const [showPage, setShowPage] = useState("False");

  const navigate = useNavigate();
  useEffect(() => {
    const checkList = filteredVaultList.map((it) => {
      if (!it.accept && it.now === "true") {
        if (
          window.confirm(
            "아직 이번 주 피드백을 확인하지 않았습니다. 피드백 페이지로 먼저 이동합니다."
          )
        ) {
          navigate("/feedback");
        } else {
          navigate("/feedback");
        }
      } else {
        setShowPage("True");
      }
    });
  }, [filteredVaultList]);
  return (
    <div className="VaultList">
      {showPage === "True" && (
        <div>
          {filteredVaultList.map((it) => (
            <VaultItem key={it.missionId} {...it} />
          ))}
        </div>
      )}
    </div>
  );
};

export default VaultList;
