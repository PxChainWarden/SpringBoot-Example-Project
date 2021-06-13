

//   function movieForm(){
//     var down = document.getElementById("GFG_DOWN");
//     var br = document.createElement("br");
//             // Create a form synamically
//             var form = document.createElement("form");
//             form.setAttribute("method", "post");
//             form.setAttribute("action", "movieAdd/");
//             form.setAttribute("modelAttribute", "movie");
//
//             // Create an input element for Full Name
//             var Name = document.createElement("input");
//             Name.setAttribute("type", "text");
//             Name.setAttribute("name", "name");
//             Name.setAttribute("placeholder", "Movie Name");
//
//              // Create an input element for date of birth
//              var ReleaseDate = document.createElement("input");
//              ReleaseDate.setAttribute("type", "date");
//              ReleaseDate.setAttribute("name", "release");
//              //DOB.setAttribute("placeholder", "DOB");
//
//              // Create an input element for emailID
//              var Type = document.createElement("input");
//              Type.setAttribute("type", "text");
//              Type.setAttribute("name", "type");
//              Type.setAttribute("placeholder", "Type");
//
//               // Create an input element for password
//               var Description = document.createElement("input");
//               Description.setAttribute("type", "text");
//               Description.setAttribute("name", "description");
//               Description.setAttribute("placeholder", "Description");
//
//                // Create an input element for retype-password
//                var Media = document.createElement("input");
//                Media.setAttribute("type", "text");
//                Media.setAttribute("name", "media");
//                Media.setAttribute("placeholder", "Media");
//                // Create an input element for retype-password
//                var Language = document.createElement("input");
//                Language.setAttribute("type", "text");
//                Language.setAttribute("name", "language");
//                Language.setAttribute("placeholder", "Language");
//
//                         // create a submit button
//                         var s = document.createElement("input");
//                         s.setAttribute("type", "submit");
//                         s.setAttribute("value", "Submit");
//
//                         // Append the full name input to the form
//                         form.appendChild(Name);
//
//                         // Inserting a line break
//                         form.appendChild(br.cloneNode());
//
//                         // Append the DOB to the form
//                         form.appendChild(ReleaseDate);
//                         form.appendChild(br.cloneNode());
//
//                         // Append the emailID to the form
//                         form.appendChild(Type);
//                         form.appendChild(br.cloneNode());
//
//                         // Append the Password to the form
//                         form.appendChild(Description);
//                         form.appendChild(br.cloneNode());
//
//                         // Append the ReEnterPassword to the form
//                         form.appendChild(Media);
//                         form.appendChild(br.cloneNode());
//                         form.appendChild(Language);
//                         form.appendChild(br.cloneNode());
//                         // Append the submit button to the form
//                         form.appendChild(s);
//
//                         document.getElementsByTagName("body")[0]
//                        .appendChild(form);
// }