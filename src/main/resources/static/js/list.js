// checkbox 클릭이 되면 
// checkbox value,data-id 가져오기

document.querySelector(".list-group").addEventListener("click", (e) => {
    // 어떤 label 안의checkbox에서 발생했는지 확인
    const chk = e.target;
    console.log(chk)
    console.log(chk.checked)

    const id = chk.closest("label").dataset.id;
    console.log(id);

    const actionForm = document.querySelector("#actionForm");
    actionForm.id.value = id;
    actionForm.completed.value = chk.checked;
    actionForm.submit();
})










