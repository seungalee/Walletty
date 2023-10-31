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
  & > div:first-child {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    white-space: nowrap;
    width: 50px;
    height: 50px;
    margin-top: 5px;
    font-size: 15px;
    padding: 5px 5px;
    color: ${(props) =>
      props.status === "DONE" ? "#a5a5a5" : "var(--strpurple)"};
    font-weight: 600;
    background-color: rgba(255, 255, 255, 0.6);
  }
  & > div:last-child {
    margin-top: 5px;
    padding-left: 15px;
    font-size: 15px;
    position: relative;
    width: 400px;
    color: var(--mygray);
  }
  & > div:last-child > div:nth-child(2) {
    position: absolute;
    top: 15px;
    left: 15px;
    font-weight: 700;
    font-size: 30px;
    color: #e8b704;
    -webkit-text-stroke: 0.2px var(--mygray);
    width: 400px;
  }
  & > div:last-child > div:last-child {
    position: absolute;
    left: 105px;
    font-size: 17px;
    top: 26px;
    font-weight: 600;
    color: var(--myblack);
  }
`;

const VaultItem = ({
  missionId,
  missionSen,
  missionMoney,
  accDeposit,
  status,
}) => {
  return (
    <div className="VaultItem">
      <VaultOutBoxStyled>
        <VaultInBoxStyled>
          <div>{accDeposit}</div>
          <VaultContentStyled status={status}>
            <div>
              입금
              <br></br>
              {status === "DONE" ? "완료" : "대기중"}
            </div>
            <div className="vault_content">
              <div>미션 : {missionSen}</div>
              <div>{missionMoney}</div>
              <div>
                {status === "DONE"
                  ? "원을 보관 중입니다"
                  : "원을 입금해 주세요"}
              </div>
            </div>
          </VaultContentStyled>
        </VaultInBoxStyled>
      </VaultOutBoxStyled>
    </div>
  );
};

export default VaultItem;
