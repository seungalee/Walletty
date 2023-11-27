import { useEffect, useState } from "react";
import VaultItem from "./VaultItem";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

const VaultList = ({ totalVaultList }) => {
  const filteredVaultList = totalVaultList.filter((it) => !it.outSafe);
  const [showPage, setShowPage] = useState("False");

  const navigate = useNavigate();
  useEffect(() => {
    const checkList = filteredVaultList.map((it) => {
      if (!it.accept && it.now === "true") {
        // if (
        //   window.confirm(
        //     "아직 이번 주 피드백을 확인하지 않았습니다. 피드백 페이지로 먼저 이동합니다."
        //   )
        // ) {
        //   navigate("/feedback");
        // } else {
        //   navigate("/feedback");
        // }
        Swal.fire({
          // text: "아직 확인하지 않은 피드백이 있습니다! 피드백 페이지로 먼저 이동합니다.",
          // imageUrl: "/assets/chatbot_mom_head.png",
          // imageWidth: 180,
          // imageHeight: 150,
          // width: 350,
          // height: 100,
          // imageAlt: "Custom image",
          // confirmButtonText: "확인",
          // customClass: "swal2-wide",
          html: `<div class="feedback_alert_box"><img src="/assets/chatbot_mom_head.png" /><div>아직 확인하지 않은 피드백이 있습니다! 피드백 페이지로 먼저 이동합니다.</div></div>`,
          customClass: "feedback-alert",
          showConfirmButton: false,
          position: "top",
          timer: 1500,
        }).then(function (result) {
          setTimeout(() => {
            navigate("/feedback");
          }, 1300);
        });
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
