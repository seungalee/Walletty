import { useEffect, useState } from "react";
import MyHeader from "../components/MyHeader";
import { useNavigate } from "react-router-dom";

const Profile = () => {
  const [profileItems, setProfileItems] = useState("");
  const [missionList, setMissionList] = useState([]);
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
          setMissionList(result);
          console.log(result);
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      navigate("/login");
    }
  }, []);
  return (
    <div>
      <MyHeader nowpage={"profile"} />
      <h1>Profile</h1>
    </div>
  );
};

export default Profile;
