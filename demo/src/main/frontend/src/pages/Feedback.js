import { useEffect, useRef, useState } from "react";
import FeedbackList from "../components/FeedbackList";
import MyHeader from "../components/MyHeader";
import { useUserState } from "../context/UserContext";

const Feedback = () => {
  const [data, setData] = useState([]);
  const [allFeedbackList, setAllFeedbackList] = useState([]);
  const dummyData = [
    {
      id: 1,
      startdate: "2023-09-01",
      enddate: "2023-10-01",
      missionEntry: "간식비",
      missionMoney: "20000",
      missionSen: "간식비를 2만원 절약해봐요!",
      now: "false",
      feedbackSen: "간식비를 지나치게 많이 썼어용",
      accDeposit: "DONE",
      successed: "true",
    },

    {
      id: 2,
      startdate: "2023-09-01",
      enddate: "2023-10-02",
      missionEntry: "쇼핑비",
      missionMoney: "25000",
      missionSen: "쇼핑비를 2만5천원 절약해봐!",
      now: "true",
      feedbackSen: "쇼핑비를 지나치게 많이 썼어",
      accDeposit: "DONE",
      successed: "false",
    },
    {
      id: 3,
      startdate: "2023-09-02",
      enddate: "2023-10-01",
      missionEntry: "간식비",
      missionMoney: "15000",
      missionSen: "간식비를 1만5천원 절약해봐!",
      now: "true",
      feedbackSen: "간식비를 지나치게 많이 썼어..",
      accDeposit: "DONE",
      successed: "true",
    },
    {
      id: 4,
      startdate: "2023-10-02",
      enddate: "2023-11-01",
      missionEntry: "택시비",
      missionMoney: "10000",
      missionSen: "택시비를 1만원 절약해봐!",
      now: "true",
      feedbackSen:
        "택시비를 지나치게 많이 썼어, 넌 돈이 하늘에서 떨어지니? 절약이 필요해. 다음달엔 노력해보자",
      accDeposit: "DONE",
      successed: "doing",
    },
  ];
  const { current: checkArray } = useRef(dummyData);
  useEffect(() => {
    setAllFeedbackList(dummyData);
    console.log(allFeedbackList);
    const newList = allFeedbackList.map(
      ({
        enddate,
        missionEntry,
        missionMoney,
        now,
        accDeposit,
        successed,
        ...rest
      }) => rest
    );
    setData(newList);
    console.log(newList);
    console.log(data);
    console.log(data.length);
  }, []);
  const { user } = useUserState();
  return (
    <div>
      <MyHeader nowpage={"feedback"} />
      <div className="feedback_page">
        <img src="/assets/chatbot_mom.png" />
        <FeedbackList feedbackList={data} listLen={data.length} />
      </div>
    </div>
  );
};

export default Feedback;
