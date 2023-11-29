import { useEffect } from "react";
import MyHeader from "../components/MyHeader";

const AudioTest = () => {
  //base64 인코딩을 해제하기 위한 함수
  const base64ToArrayBuffer = (base64) => {
    //인코딩 해제해 바이너리 스트링으로 받기
    const binary_string = window.atob(base64);
    //해당 길이가 필요해 변수에 적재
    const len = binary_string.length;
    //??이부분 ~ 밑에 루프가 이해가 잘 되지 않는다.
    const bytes = new Uint8Array(len);

    for (var i = 0; i < len; i++) {
      bytes[i] = binary_string.charCodeAt(i);
    }

    return bytes.buffer;
  };
  //fileSrc는 DB에서 읽어온 파일의 경로를 UI에서 클릭했을 시 보내주는 파라미터이다.
  const setAudio = (fileSrc) => {
    var v = this;
    fetch("/cusvoice/audio", {
      method: "POST",
      body: JSON.stringify({
        fileSrc: fileSrc,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((result) => result.json())
      .then((result) => {
        console.log(result);
        //Controller에서 통신해 받아온 값을 base64인코딩을 해제한다.
        const data = base64ToArrayBuffer(result.audio);

        //base64인코딩을 해제한 바이너리data를 변수에 담는다.
        const uInt8Array = new Uint8Array(data);

        // Blob Object 를 생성한다.
        const blob = new Blob([uInt8Array], { type: "audio/mp3" });

        //blob으로 만든 객체를 재생시키기 위해 url로 주소를 만들어 객체에 담는다.
        const url = URL.createObjectURL(blob);

        //해당 주소를 audio객체를 만들어 소스에 넣고 플레이시킨다.
        const audio = new Audio();
        audio.src = url;
        audio.muted = true;
        audio.play();
        audio.muted = false;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div>
      <MyHeader />
      <button onClick={setAudio("C://sample.mp4")}>버튼</button>
    </div>
  );
};

export default AudioTest;
