//     function onChangeTestsubject(response) {
//
//         //alert(response.testsubject);
//         $('result1').update(response.testsubject);
//         $('result1').setStyle({
//            backgroundColor: '#FFFF5C'
//            });
//
//
//     }


function FCKeditor_OnComplete( editorInstance )
{

    fckeditor_word_count(editorInstance);
    editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count);


}

function fckeditor_word_count(editorInstance) {

    var matches = editorInstance.GetData().replace(/<[^<|>]+?>|&nbsp;/gi,' ').match(/\b/g);
    var count = 0;
    if(matches) {
        count = matches.length/2;
    }
    if(editorInstance.Name == "passeditorone")
    {
        $('word_count').innerHTML = count + " word" + (count == 1 ? "" : "s");
        
    }
    if(editorInstance.Name == "passeditortwo") {

        $('word_count2').innerHTML = count + " word" + (count == 1 ? "" : "s");
    }
    if(editorInstance.Name == "pass1")
    {
        $('word_count').innerHTML = count + " word" + (count == 1 ? "" : "s");
        
    }
    if(editorInstance.Name == "pass2")
    {
        $('word_count2').innerHTML = count + " word" + (count == 1 ? "" : "s");

    }
}

