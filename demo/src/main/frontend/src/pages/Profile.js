import React, { useEffect, useState } from "react";
import MyHeader from "../components/MyHeader";
import { useNavigate } from "react-router-dom";

const Profile = () => {
  const [profileItems, setProfileItems] = useState("");
  const [goalEntryList, setGoalEntryList] = useState([]);
  const navigate = useNavigate();
  const id = localStorage.getItem("memberId");
  useEffect(() => {
    if (localStorage.getItem("isLoggedIn") === "true") {
      fetch("/profile", {
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
          setProfileItems(result);
          console.log(result);
        })
        .catch((err) => {
          console.log(err);
        });

      console.log(profileItems);
    } else {
      navigate("/login");
    }
  }, []);

  useEffect(() => {
    const engGoalEntryList = [
      profileItems.goalEntry1,
      profileItems.goalEntry2,
      profileItems.goalEntry3,
    ];
    setGoalEntryList(engGoalEntryList);
    console.log(goalEntryList);
  }, [profileItems]);

  return (
    <div>
      <MyHeader nowpage={"profile"} />
      <div className="profile_page">
        <div className="profile_info">
          <div className="profile_img">
            <img src={`/assets/character_${profileItems.position}.png`} />
            <div>{profileItems.position}</div>
            <div>Level. {profileItems.level}</div>
          </div>
          <div className="profile_infos">
            <div>{profileItems.memberId}님, 환영합니다 :)</div>
            <div>
              <div>📌 절약 집중 항목</div>
              <div>
                {goalEntryList.map((data) => (
                  <div key={data}>
                    {data === "eatout" && "외식"}
                    {data === "deliver" && "배달"}
                    {data === "cafe" && "카페"}
                    {data === "snack" && "간식"}
                    {data === "taxi" && "택시"}
                    {data === "shopping" && "쇼핑"}
                    {data === "beauty" && "미용"}
                  </div>
                ))}
              </div>
            </div>
            <div>
              현재까지 총 <div>{profileItems.totalSavingMoney}원</div> 절약
              성공!
            </div>
            <div>
              이번주 <div>{profileItems.weekTotalAmount}원</div> 소비 중!
            </div>
          </div>
        </div>
        <div className="profile_mission">
          <div>
            총 <div>{profileItems.missionCnt}</div>개의 미션 중{" "}
            <div>{profileItems.successCnt}</div>개 성공 😊
          </div>
          <div>
            <div>최근 성공한 미션</div>
            <div>
              {profileItems.successMission1 && (
                <div>{profileItems.successMission1}</div>
              )}
              {profileItems.successMission2 && (
                <div>{profileItems.successMission2}</div>
              )}
              {profileItems.successMission3 && (
                <div>{profileItems.successMission3}</div>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
