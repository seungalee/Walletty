import { useState } from "react";
import { useUserState } from "../context/UserContext";
import Button from "../components/Button";
import { useNavigate } from "react-router-dom";

const consumeList = [
  { value: "eat_out", name: "외식" },
  { value: "delivery", name: "배달" },
  { value: "cafe", name: "카페" },
  { value: "snack", name: "간식" },
  { value: "taxi", name: "택시" },
  { value: "shopping", name: "쇼핑" },
  { value: "beauty", name: "미용" },
];

const Survey = () => {
  const navigate = useNavigate();
  const { user } = useUserState();
  const [fixedCheckedList, setFixedCheckedList] = useState([]);
  const [goalCheckedList, setGoalCheckedList] = useState([]);
  const [isChecked, setIsChecked] = useState(false);
  const [goalRight, setGoalRight] = useState(false);
  const storageFixedList = JSON.parse(localStorage.getItem("fixed_entry"));
  const storageGoalList = JSON.parse(localStorage.getItem("goal_entry"));

  const fixedCheckedItemHandler = (value, isChecked) => {
    if (isChecked) {
      setFixedCheckedList((prev) => [...prev, value]);
      return;
    } else if (!isChecked) {
      setFixedCheckedList(fixedCheckedList.filter((item) => item !== value));
      return;
    }
    return;
  };

  const goalCheckedItemHandler = (value, isChecked) => {
    if (isChecked) {
      setGoalCheckedList((prev) => [...prev, value]);
      return;
    } else if (!isChecked && goalCheckedList.includes(value)) {
      setGoalCheckedList(goalCheckedList.filter((item) => item !== value));
      return;
    }
    return;
  };

  const fixedCheckHandler = (e, value) => {
    setIsChecked(!isChecked);
    fixedCheckedItemHandler(value, e.target.checked);
  };

  const goalCheckHandler = (e, value) => {
    setIsChecked(!isChecked);
    goalCheckedItemHandler(value, e.target.checked);
  };

  const allSubmitHandler = (e) => {
    if (fixedCheckedList.length > 2) {
      alert("고정 지출은 최대 두 개 선택 가능합니다");
    } else if (goalCheckedList.length !== 3) {
      alert("절약하고 싶은 항목은 반드시 세 개 선택해야 합니다.");
    } else if (
      goalCheckedList.filter((it) => fixedCheckedList.includes(it)).length !== 0
    ) {
      console.log(
        goalCheckedList.filter((it) => fixedCheckedList.includes(it))
      );
      alert(
        "두 질문은 중복되는 항목을 가질 수 없습니다. 각자 다른 항목을 선택해 주세요"
      );
    } else {
      setGoalRight(!goalRight);
      localStorage.setItem("fixed_entry", JSON.stringify(fixedCheckedList));
      localStorage.setItem("goal_entry", JSON.stringify(goalCheckedList));
      navigate("/survey-goalmoney");
    }
  };

  return (
    <div>
      <h2>가입해주셔서 갑사합니다!</h2>
      <h4>
        사용자님의 소비 습관을 파악하기 위한 설문을 제출하면 서비스가
        시작됩니다.
      </h4>
      <div className="survey_qs">
        <div className="survey_q_box">
          <div className="survey_q_text">
            소비하는 항목 중 줄이기 힘든 고정지출에는 어떤 것이 있나요? (최대
            2개 선택)
          </div>
          <div className="checkbox_list_box">
            {consumeList.map((item) => (
              <div className="checkbox_items" key={`fixed_${item.value}`}>
                <input
                  className={`${
                    fixedCheckedList.includes(item)
                      ? "checkbox_item_checked"
                      : ""
                  }`}
                  type="checkbox"
                  id={`fixed_${item.value}`}
                  checked={fixedCheckedList.includes(item)}
                  onChange={(e) => fixedCheckHandler(e, item)}
                />

                <label htmlFor={`fixed_${item.value}`}>{item.name}</label>
              </div>
            ))}
          </div>
        </div>
        <div className="survey_q_box">
          <div className="survey_q_text">
            가장 절약하고 싶은 항목 세 가지를 선택해 주세요.
          </div>
          <div className="checkbox_list_box">
            {consumeList.map((item) => (
              <div className="checkbox_items" key={`goal_${item.value}`}>
                <input
                  className={`${
                    goalCheckedList.includes(item)
                      ? "checkbox_item_checked"
                      : ""
                  }`}
                  type="checkbox"
                  id={`goal_${item.value}`}
                  checked={goalCheckedList.includes(item)}
                  onChange={(e) => goalCheckHandler(e, item)}
                />
                <label htmlFor={`goal_${item.value}`}>{item.name}</label>
              </div>
            ))}
          </div>
        </div>
        <Button text={"선택 완료"} onClick={allSubmitHandler} type={"survey"} />
        {/* {goalRight && (
          <div className="survey_q_box">
            <div className="survey_q_text">
              각 항목별로 줄이고 싶은 목표 금액을 입력해 주세요. 목표 금액은
              현재 해당 항목 지출량의 5% 이상이어야 합니다.
            </div>
          </div>
        )} */}
      </div>
    </div>
  );
};

export default Survey;