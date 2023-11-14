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
  const [nowMission, setNowMission] = useState([]);

  useEffect(() => {
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
    console.log(allMissionList);
    const nowMissionItem = allMissionList.filter(
      (it) => it.accept && it.now === "true"
    );
    console.log(nowMissionItem);
    setNowMission((nowMission) => nowMissionItem);
    console.log(nowMission);
  }, [allFeedbackList, allMissionList]);

  const { user } = useUserState();
  return (
    <div>
      <MyHeader nowpage={"feedback"} />
      {data[0] && (
        <div className="feedback_page">
          <div className="feedback_img">
            <div>
              다음 주에는 소비 습관이 개선되어 있기를 바랄게. 엄마는 널 믿어! 😘
            </div>
            <img src="/assets/chatbot_mom.png" />
          </div>
          <FeedbackList feedbackList={data} listLen={data.length} />
          <div className="feedback_img">
            {/* {nowMission ? (
              <div>
                이번 주에는 {nowMission[0].missionEntry === "eatout" && "외식"}
                {nowMission[0].missionEntry === "deliver" && "배달"}
                {nowMission[0].missionEntry === "cafe" && "카페"}
                {nowMission[0].missionEntry === "snack" && "간식"}
                {nowMission[0].missionEntry === "taxi" && "택시"}
                {nowMission[0].missionEntry === "shopping" && "쇼핑"}
                {nowMission[0].missionEntry === "beauty" && "미용"}비를
                줄여봐야겠다!
              </div>
            ) : (
              <div>이번 주에는 무슨 미션을 받게 될까?</div>
            )} */}
            <div>이번 주에는 무슨 미션을 받게 될까? 😆</div>
            <img src="/assets/character_org.png" />
          </div>
        </div>
      )}
    </div>
  );
};

export default Feedback;
