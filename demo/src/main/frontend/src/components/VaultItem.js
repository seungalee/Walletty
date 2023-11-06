import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const VaultOutBoxStyled = styled.div`
  width: 530px;
  height: 130px;
  background-color: #bbbaba;
  margin: 20px 10px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const VaultInBoxStyled = styled.div`
  width: 480px;
  height: 100px;
  background-color: #dddddd;
  padding: 0px 10px;
  & > div:first-child {
    margin-top: 2px;
    margin-left: 1px;
    font-size: 13px;
    color: var(--mygray);
  }
`;

const VaultContentStyled = styled.div`
  display: flex;
  position: relative;
  & > div:first-child {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    white-space: nowrap;
    width: 50px;
    height: 50px;
    margin-top: 17px;
    font-size: 15px;
    padding: 5px 5px;
    color: ${(props) => (props.insafe ? "#a5a5a5" : "var(--strpurple)")};
    font-weight: 600;
    background-color: rgba(255, 255, 255, 0.6);
  }
  & > div:nth-child(2) {
    margin-top: 18px;
    padding-left: 15px;
    font-size: 15px;
    position: relative;
    width: 400px;
    color: var(--mygray);
  }
  & > div:nth-child(2) > div:nth-child(2) {
    position: absolute;
    top: 15px;
    left: 15px;
    font-weight: 700;
    font-size: 30px;
    color: #e8b704;
    -webkit-text-stroke: 0.2px var(--mygray);
    width: 400px;
  }
  & > div:nth-child(2) > div:last-child {
    position: absolute;
    left: 105px;
    font-size: 17px;
    top: 26px;
    font-weight: 600;
    color: var(--myblack);
  }
  & > div:nth-child(3) {
    margin-top: 7px;
    position: absolute;
    display: flex;
    flex-direction: column;
    right: 0px;
    top: 0px;
  }
`;

const ButtonStyled = styled.button`
  width: 80px;
  height: 30px;
  margin: 5px 5px;
  border: none;
  font-family: "Noto Sans KR", sans-serif;
  justify-content: center;
  background-color: white;
  &:hover {
    background-color: var(--lgmidpurple);
  }
`;

const VaultItem = ({ missionId, missionSen, missionMoney, inSafe }) => {
  const navigate = useNavigate();

  const depositHandler = () => {
    navigate("/toss");
  };

  const depositDoneHandler = () => {
    fetch("/safe", {
      method: "POST",
      body: JSON.stringify({
        isSafe: 1,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((result) => result.json())
      .then((result) => {
        console.log(result);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <div className="VaultItem">
      <VaultOutBoxStyled>
        <VaultInBoxStyled>
          <VaultContentStyled insafe={inSafe}>
            <div>
              입금
              <br></br>
              {inSafe ? "완료" : "대기중"}
            </div>
            <div className="vault_content">
              <div>미션 : {missionSen}</div>
              <div>{missionMoney}</div>
              <div>{inSafe ? "원을 보관 중입니다" : "원을 입금해 주세요"}</div>
            </div>
            {!inSafe && (
              <div className="vault_buttons">
                <ButtonStyled onClick={depositHandler}>입금하기</ButtonStyled>
                <ButtonStyled onClick={depositDoneHandler}>
                  입금완료
                </ButtonStyled>
              </div>
            )}
          </VaultContentStyled>
        </VaultInBoxStyled>
      </VaultOutBoxStyled>
    </div>
  );
};

export default VaultItem;
