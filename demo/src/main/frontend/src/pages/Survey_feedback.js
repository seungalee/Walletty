import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Button from "../components/Button";

const Survey_feedback = () => {
  const navigate = useNavigate();
  const [normChecked, setNormChecked] = useState("false");
  const [weakChecked, setWeakChecked] = useState("false");
  const isWeakChecked = () => {
    const checkboxnow = document.getElementById("feedback_weak");
    setWeakChecked(checkboxnow.checked.toString());
    console.log(weakChecked);
  };
  const isNormChecked = () => {
    const checkboxnow = document.getElementById("feedback_norm");
    setNormChecked(checkboxnow.checked.toString());
    console.log(normChecked);
  };
  const onSubmitHandler = () => {
    navigate("/survey");
  };
  return (
    <div>
      <h2>가입해주셔서 감사합니다!</h2>
      <h4>
        사용자님의 소비 습관을 파악하기 위한 설문을 제출하면 서비스가
        시작됩니다.
      </h4>
      <div className="survey_qs">
        <div className="survey_q_box">
          <div className="survey_q_text">
            잔소리 AI 챗봇인 Monneybot이 소비내역을 분석해서 잔소리를
            해줄거예요. <br></br>어떻게 잔소리를 해주면 좋을지 선택해주세요:)
          </div>
          <div className="checkbox_list_box_feedback">
            <img src="/assets/chatbot_mom.png" />
            <div className="checkbox_items_box_feedback">
              <div className="checkbox_items_feedback">
                <input
                  className={`${
                    weakChecked === "true" ? "checkbox_item_checked" : ""
                  }`}
                  type="checkbox"
                  id="feedback_weak"
                  onClick={isWeakChecked}
                />
                <label htmlFor="feedback_weak">약한 강도 잔소리</label>
              </div>
              <div className="checkbox_items_feedback">
                <input
                  className={`${
                    normChecked === "true" ? "checkbox_item_checked" : ""
                  }`}
                  type="checkbox"
                  id="feedback_norm"
                  onClick={isNormChecked}
                />
                <label htmlFor="feedback_norm">기본 강도 잔소리</label>
              </div>
            </div>
          </div>
        </div>
        <Button text={"선택 완료"} onClick={onSubmitHandler} type={"survey"} />
      </div>
    </div>
  );
};

export default Survey_feedback;
