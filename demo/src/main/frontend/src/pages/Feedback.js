import { useEffect, useRef, useState } from "react";
import FeedbackList from "../components/FeedbackList";
import MyHeader from "../components/MyHeader";
import { useUserState } from "../context/UserContext";
import { useNavigate } from "react-router-dom";

const Feedback = () => {
  const navigate = useNavigate();
  const id = localStorage.getItem("memberId");
  const [data, setData] = useState([]);
  const [allMissionList, setAllMissionList] = useState([]);
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
  ];
  useEffect(() => {
    // setAllFeedbackList(dummyData);
    // console.log(allFeedbackList);
    // const newList = allFeedbackList.map(
    //   ({
    //     enddate,
    //     missionEntry,
    //     missionMoney,
    //     now,
    //     accDeposit,
    //     successed,
    //     ...rest
    //   }) => rest
    // );
    // setData(newList);
    // console.log(newList);
    // console.log(data);
    console.log("리렌더링1");
    if (localStorage.getItem("isLoggedIn") === "true") {
      fetch("/chat-gpt/feedback", {
        method: "POST",
        body: JSON.stringify({
          memberId: id,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((result) => result.json())
        .then((result) => {
          setAllFeedbackList(result);
          console.log("피드백2", result, allFeedbackList);
        })
        .catch((err) => {
          console.log(err);
        });

      fetch("/chat-gpt/mission", {
        method: "POST",
        body: JSON.stringify({
          memberId: id,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((result) => result.json())
        .then((result) => {
          setAllMissionList(result);
          console.log("mission3", result, allMissionList);
        })
        .catch((err) => {
          console.log(err);
        });
      //startdate 기준 합치기
      //console.log(allFeedbackList);
      // console.log(allMissionList);
    } else {
      navigate("/login");
    }
  }, []);

  useEffect(() => {
    const map = new Map();
    allFeedbackList.forEach((item) => map.set(item.startdate, item));
    allMissionList.forEach((item) =>
      map.set(item.startDate, { ...map.get(item.startDate), ...item })
    );

    const missionFeedbackList = Array.from(map.values());
    const newList = missionFeedbackList.map(
      ({
        memberId,
        missionEntry,
        missionMoney,
        now,
        content,
        comment,
        ...rest
      }) => rest
    );
    setData(newList);
    console.log("data4", data);
  }, [allFeedbackList, allMissionList]);

  const { user } = useUserState();
  return (
    <div>
      <MyHeader nowpage={"feedback"} />
      {data[0] && (
        <div className="feedback_page">
          <img src="/assets/chatbot_mom.png" />
          <FeedbackList feedbackList={data} listLen={data.length} />
        </div>
      )}
    </div>
  );
};

export default Feedback;
